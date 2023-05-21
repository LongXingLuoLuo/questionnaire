package com.longxingluoluo.questionnaire.util;

import com.alibaba.fastjson.JSONObject;
import com.longxingluoluo.questionnaire.entity.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project questionnaire
 * Created on 2023/5/21 上午 10:38
 *
 * @author 龙星洛洛
 */
public class ExcelExport {
    public static void exportExcel(List<QuestionnaireAnswer> questionnaireAnswerList, Questionnaire questionnaire, HttpServletResponse response){
        if (questionnaire == null){
            return;
        }
        try(XSSFWorkbook workbook = new XSSFWorkbook()){
            XSSFSheet sheet = workbook.createSheet();
            // excel 表头
            // 当教师名重复或者课程名重复时, 出错
            List<String> headerIds = new ArrayList<>();
            List<String> headers = new ArrayList<>();
            if (questionnaire.getGradeList() != null){
                headers.add("年级");
                headerIds.add("grade");
            }
            if (questionnaire.getProfessionalList() != null){
                headers.add("专业");
                headerIds.add("professional");
            }
            for (Curriculum curriculum :
                    questionnaire.getCurriculumList()) {
                headers.add(curriculum.getName());
                headerIds.add("curriculum-" + curriculum.getId());
            }
            for (Teacher teacher :
                    questionnaire.getTeacherList()) {
                headers.add(teacher.getName());
                headerIds.add("teacher-" + teacher.getId());
            }
            headers.add("学生自己成绩排名");
            headerIds.add("self-evaluation");
            Row[] rows = new Row[questionnaireAnswerList.size() + 1];
            for (int i = 0; i < rows.length; i++) {
                rows[i] = sheet.createRow(i);
            }

            // 添加表头
            for (int i = 0; i < headers.size(); i++) {
                rows[0].createCell(i).setCellValue(headers.get(i));
            }

            // 添加数据
            for (int i = 0; i < questionnaireAnswerList.size(); i++) {
                if (questionnaireAnswerList.get(i) == null){
                    continue;
                }
                Map<String, String> headerIdToValue = new HashMap<>();
                QuestionnaireAnswer questionnaireAnswer = questionnaireAnswerList.get(i);
                if (questionnaireAnswer.getGrade() != null){
                    headerIdToValue.put("grade", questionnaireAnswer.getGrade().getName());
                }
                if (questionnaireAnswer.getProfessional() != null){
                    headerIdToValue.put("professional", questionnaireAnswer.getProfessional().getName());
                }
                for (CurriculumEvaluation curriculumEvaluation :
                        questionnaireAnswer.getCurriculumEvaluationList()) {
                    headerIdToValue.put("curriculum-" + curriculumEvaluation.getCurriculum().getId(), String.valueOf(curriculumEvaluation.getEvaluation()));
                }
                for (TeacherEvaluation teacherEvaluation :
                        questionnaireAnswer.getTeacherEvaluationList()) {
                    headerIdToValue.put("teacher-" + teacherEvaluation.getTeacher().getId(), String.valueOf(teacherEvaluation.getEvaluation()));
                }
                headerIdToValue.put("self-evaluation", String.valueOf(questionnaireAnswer.getSelfEvaluation()));
                for (int j = 0; j < headerIds.size(); j++) {
                    String value = headerIdToValue.get(headerIds.get(j));
                    try{
                        double numValue = Double.parseDouble(value);
                        rows[i + 1].createCell(j).setCellValue(numValue);
                    } catch (NumberFormatException | NullPointerException e){
                        rows[i + 1].createCell(j).setCellValue(value);
                    }
                }
            }
            try(ServletOutputStream outputStream = response.getOutputStream()) {
                String filename = URLEncoder.encode(questionnaire.getName() + ".xlsx", "UTF-8");
                response.setContentType("application/vnd.ms-xlsx");
                response.setHeader("Content-Disposition", "attachment; filename=" + filename);
                workbook.write(outputStream);
                outputStream.flush();
            } catch (IOException e){
                e.printStackTrace();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
