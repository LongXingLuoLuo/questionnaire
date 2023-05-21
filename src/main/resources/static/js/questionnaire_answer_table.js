function deleteAction(questionnaireID){
    $.ajax({
        url: "/questionnaire_answer/export/" + questionnaireID,
        type: "get",
        error: function (message){
            alert("导出为 excel 请求失败");
        }
    })
}
function exportActionFormatter(value, row, index) {
    let result = $("<div><a><span></span></a></div>");
    result.find("a").attr("href", "/questionnaire_answer/export/" + value)
    result.find("a").addClass("btn btn-xs green")
    result.find("span").addClass("glyphicon glyphicon-export")
    return result.html();
}

function deleteActionFormatter(value, row, index) {
    let result = $("<div><a><span></span></a></div>");
    result.find("a").attr("href", "/questionnaire_answer/export/" + value)
    result.find("a").addClass("btn btn-xs green")
    result.find("span").addClass("glyphicon glyphicon-export")
    return result.html();
}