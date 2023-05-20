/**
 * 将 id 为 sID 的表单结果转为 json 格式
 * @param sID 表单 id
 * @returns {json} json类型的表单数据
 */
function formJson(sID) {
    let arr = $("#" + sID).serializeArray();
    let jsonStr = "";
    jsonStr += "{";
    for (const arrElement of arr) {
        jsonStr += '"' + arrElement.name + '":"' + arrElement.value + '",';
    }
    jsonStr = jsonStr.substring(0, (jsonStr.length - 1));
    jsonStr += "}";
    return JSON.parse(jsonStr);
}

/**
 * 生成 div 下所有的 $(":in-range") 的 json
 * @param sClass 指定的 div 类
 * @returns {json} json类型的表单数据
 */
function divJson(sClass) {
    let arr = [];
    $(sClass).each(function () {
        let oRange = $(this).find(":in-range");
        arr.push({
            "id" : oRange.attr("name"),
            "evaluation": oRange.val()
        });
    });
    return JSON.parse(JSON.stringify(arr));
}

function rgbToHex(rgb) {
    return "#" +
        ("0" + rgb[0].toString(16)).slice(-2) +
        ("0" + rgb[1].toString(16)).slice(-2) +
        ("0" + rgb[2].toString(16)).slice(-2);
}

function peekHex(color1, color2, weight) {
    let w1 = 1 - weight;
    let rgb = [Math.round(color1[0] * w1 + color2[0] * weight),
        Math.round(color1[1] * w1 + color2[1] * weight),
        Math.round(color1[2] * w1 + color2[2] * weight),];
    return rgbToHex(rgb);
}

const color1 = [0xC2, 0xFF, 0xDB];
const color2 = [0x46, 0x5E, 0xFB];

function evaluationDivInit(oDiv, min, max, step, value) {
    let oRange = $(oDiv).find(":in-range");
    let oValue = $(oDiv).find(".evaluation-value");
    let oShow = $(oDiv).find(".evaluation-show");
    oRange.attr("min", min);
    oRange.attr("max", max);
    oRange.attr("step", step);
    const diameter = 25;
    oRange.on("input property-change", () => {
        let weight = (oRange.val() - min) / (max - min);

        // * 标签跟随滑块移动
        let leftOffset = oRange.position().left + diameter / 2 + oRange.width() * weight * (1 - diameter / oRange.width()) - oShow.width() / 2;
        let topOffset = oRange.position().top - oShow.height() - 12;
        oShow.css("left", leftOffset + "px");
        oShow.css("top", topOffset + "px");
        oShow.show();

        // * 输入结果展示
        oValue.text(oRange.val());

        // * 滑块轨道背景渐变
        oRange.css('background', 'linear-gradient(to right, ' + rgbToHex(color1) + ',' + peekHex(color1, color2, weight) + " " + weight * 100 + '%, white 0%, white)');
    }).val(value);
    oRange.focusout(function () {
        oShow.hide();
    })
    oValue.text(oRange.val());
    let weight = (oRange.val() - min) / (max - min);
    oRange.css('background', 'linear-gradient(to right, ' + rgbToHex(color1) + ',' + peekHex(color1, color2, weight) + " " + weight * 100 + '%, white 0%, white)');
    oShow.hide();
}

$(function () {
    $(".evaluation-curriculum-div").each(function () {
        evaluationDivInit(this, 0, 10, 1, 5);
    })
    $(".evaluation-teacher-div").each(function () {
        evaluationDivInit(this, 0, 10, 1, 5);
    })
    $(".evaluation-self-div").each(function () {
        evaluationDivInit(this, 5, 100, 5, 5);
    })

    $("#submit").click(function () {
        let data = {
            "questionnaireId": $("#questionnaire-id-input").val(),
            "gradeId": $("#grade-select").val(),
            "professionalId": $("#professional-select").val(),
            "curriculumEvaluationList": divJson(".evaluation-curriculum-div"),
            "teacherEvaluationList": divJson(".evaluation-teacher-div"),
            "selfEvaluation": $("#self-evaluation").val(),
        };
        console.log(JSON.parse(JSON.stringify(data)));
        $.ajax({
            url: "/questionnaire_answer/add",
            type: "post",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(data),
            success: function (message) {
                message = JSON.parse(message);
                if (message["msg"] === true){
                    alert("问卷填写提交成功");
                } else {
                    alert("问卷填写提交失败");
                }
            },
            error: function (message) {
                alert("问卷填写提交请求失败");
            }
        })
    })
})