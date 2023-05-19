
$(function () {
    let nameInput = $("#name-input");
    let gradeSelect = $("#gradeList-select");
    let professionalSelect = $("#professionalList-select");
    let teacherSelect = $("#teacherList-select");
    let curriculumSelect = $("#curriculumList-select");

    $("#submit-btn").click(function () {
        let temp = {
            "name": nameInput.val(),
            "gradeList": gradeSelect.val(),
            "professionalList": professionalSelect.val(),
            "curriculumList": curriculumSelect.val(),
            "teacherList": teacherSelect.val()
        };
        $.ajax({
            url: "/questionnaire/doAdd",
            type: "post",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(temp),
            success: function (message) {
                if (message["msg"] === true){
                    alert("添加成功");
                } else {
                    alert("添加失败");
                }
            },
            error: function (message) {
                alert("访问失败");
            }
        })
    })
})