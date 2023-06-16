const gradeDeleteUrl = '/grade/delete/';
const professionalDeleteUrl = '/professional/delete/';
const curriculumDeleteUrl = '/curriculum/delete/';
const teacherDeleteUrl = '/teacher/delete/';
const questionnaireDeleteUrl = '/questionnaire/delete/';

const gradeAddUrl = '/grade/add';
const professionalAddUrl = '/professional/add';
const curriculumAddUrl = '/curriculum/add';
const teacherAddUrl = '/teacher/add';

function deleteAjax(sUrl, successCallback) {
    console.log(sUrl)
    $.ajax({
        url: sUrl,
        type: "delete",
        success: function (message) {
            if (message["msg"] === true) {
                successCallback();
            } else {
                alert("删除失败");
            }
        },
        error: function () {
            alert("删除请求失败");
        }
    })
}

function addAjax(sUrl, data, successCallback) {
    $.ajax({
        url: sUrl,
        type: "post",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(data),
        success: function (message) {
            if (message["msg"] === true) {
                successCallback();
            } else {
                alert("添加失败");
            }
        },
        error: function () {
            alert("添加请求失败");
        }
    })
}

function checkInputNull(oInput) {
    if (oInput.val() == null || oInput.val().trim() === "") {
        oInput.focus();
        return false;
    } else {
        return true;
    }
}

function deleteGradeAction(id) {
    deleteAjax(gradeDeleteUrl + id, function () {
        $("#grade-manage-table").bootstrapTable().bootstrapTable('refresh');
    });
}

function deleteProfessionalAction(id) {
    deleteAjax(professionalDeleteUrl + id, function () {
        $("#professional-manage-table").bootstrapTable().bootstrapTable('refresh');
    });
}

function deleteCurriculumAction(id) {
    deleteAjax(curriculumDeleteUrl + id, function () {
        $("#curriculum-manage-table").bootstrapTable().bootstrapTable('refresh');
    });
}

function deleteTeacherAction(id) {
    deleteAjax(teacherDeleteUrl + id, function () {
        $("#teacher-manage-table").bootstrapTable().bootstrapTable('refresh');
    });
}

function deleteQuestionnaireAction(id) {
    deleteAjax(questionnaireDeleteUrl + id, function () {
        $("#questionnaire-manage-table").bootstrapTable().bootstrapTable('refresh');
    });
}

function removeAction() {
    let result = $("<div><a><span></span></a></div>");
    result.find("a").attr("href", "javascript:;");
    result.find("a").addClass("btn btn-xs btn-danger");
    result.find("span").addClass("glyphicon glyphicon-remove");
    return result;
}

function deleteGradeFormatter(value, row, index) {
    let result = removeAction();
    result.find("a").attr("onclick", "deleteGradeAction(" + value + ")");
    return result.html();
}

function deleteProfessionalFormatter(value, row, index) {
    let result = removeAction();
    result.find("a").attr("onclick", "deleteProfessionalAction(" + value + ")");
    return result.html();
}

function deleteCurriculumFormatter(value, row, index) {
    let result = removeAction();
    result.find("a").attr("onclick", "deleteCurriculumAction(" + value + ")");
    return result.html();
}

function deleteTeacherFormatter(value, row, index) {
    let result = removeAction();
    result.find("a").attr("onclick", "deleteTeacherAction(" + value + ")");
    return result.html();
}

function deleteQuestionnaireFormatter(value, row, index) {
    let result = removeAction();
    result.find("a").attr("onclick", "deleteQuestionnaireAction(" + value + ")");
    return result.html();
}

function copyToClip(val)     {

    if (navigator.clipboard && window.isSecureContext) {
        // navigator clipboard 向剪贴板写文本
        return navigator.clipboard.writeText(val)
    } else {
        // 创建text area
        const textArea = document.createElement('textarea')
        textArea.value = val
        // 使text area不在viewport，同时设置不可见
        document.body.appendChild(textArea)
        textArea.focus()
        textArea.select()
        return new Promise((res, rej) => {
            // 执行复制命令并移除文本框
            document.execCommand('copy') ? res() : rej()
            textArea.remove()
        })
    }
}

function visitUrlFormatter(value, row, index) {
    let result = $("<div><a><span></span></a></div>");
    result.find("a").attr("href", "javascript:;");
    result.find("a").addClass("btn btn-xs btn-default");
    result.find("a").attr("onclick", "copyToClip('" + value + "')")
    result.find("span").addClass("glyphicon glyphicon-copy");
    return result.html();
}

function answerUrlFormatter(value, row, index) {
    let result = $("<div><a><span></span></a></div>");
    result.find("a").attr("href", value);
    result.find("a").addClass("btn btn-xs btn-info");
    result.find("span").addClass("glyphicon glyphicon-sort");
    return result.html();
}


$(function () {
    $("#grade-add-btn").click(function () {
        let oInput = $("#grade-name-input");
        if (!checkInputNull(oInput)) {
            return;
        }
        let data = {
            "name": oInput.val().trim()
        }
        addAjax(gradeAddUrl, data, function () {
            $("#grade-manage-table").bootstrapTable().bootstrapTable('refresh');
            oInput.val("");
        });
    })
    $("#professional-add-btn").click(function () {
        let oInput = $("#professional-name-input");
        if (!checkInputNull(oInput)) {
            return;
        }
        let data = {
            "name": oInput.val().trim()
        }
        addAjax(professionalAddUrl, data, function () {
            $("#professional-manage-table").bootstrapTable().bootstrapTable('refresh');
            oInput.val("");
        });
    })
    $("#curriculum-add-btn").click(function () {
        let oInput = $("#curriculum-name-input");
        if (!checkInputNull(oInput)) {
            return;
        }
        let data = {
            "name": oInput.val().trim()
        }
        addAjax(curriculumAddUrl, data, function () {
            $("#curriculum-manage-table").bootstrapTable().bootstrapTable('refresh');
            oInput.val("");
        });
    })
    $("#teacher-add-btn").click(function () {
        let oInput = $("#teacher-name-input");
        if (!checkInputNull(oInput)) {
            return;
        }
        let data = {
            "name": oInput.val().trim()
        }
        addAjax(teacherAddUrl, data, function () {
            $("#teacher-manage-table").bootstrapTable().bootstrapTable('refresh');
            oInput.val("");
        });
    })
})