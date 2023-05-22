function deleteAction(id) {
    $.ajax({
        url: "/questionnaire_answer/delete/" + id,
        type: "delete",
        success: function (message) {
            if (message["msg"] === true) {
                $("#questionnaire-answer-table").bootstrapTable().bootstrapTable('refresh');
            } else {
                alert("删除失败");
            }
        },
        error: function (message) {
            alert("删除请求失败");
        }
    })
}

function exportExcel(id) {
    window.location.href = "/questionnaire_answer/export/" + id;
}

function enterManagement() {
    window.location.href = "/admin/management";
}


function exportActionFormatter(value, row, index) {
    let result = $("<div><a><span></span></a></div>");
    result.find("a").attr("href", "/questionnaire_answer/export/" + value)
    result.find("a").addClass("btn btn-xs btn-success")
    result.find("span").addClass("glyphicon glyphicon-export")
    return result.html();
}

function deleteActionFormatter(value, row, index) {
    let result = $("<div><a><span></span></a></div>");
    result.find("a").attr("href", "javascript:;")
    result.find("a").addClass("btn btn-xs btn-danger")
    result.find("a").attr("onclick", "deleteAction(" + value + ")")
    result.find("span").addClass("glyphicon glyphicon-remove")
    return result.html();
}