
$(function () {
    let nameInput = $("#name-input");
    let gradeSelect = $("#gradeList-select");
    let professionalSelect = $("#professionalList-select");
    let teacherSelect = $("#teacherList-select");
    let curriculumSelect = $("#curriculumList-select");

    $("#submit-btn").click(function () {
        if(nameInput.val() === null || nameInput.val().trim() === ""){
            nameInput.val("")
            nameInput.focus()
            return;
        }
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
                    alert("问卷添加成功");
                    document.location.href = "/admin/management";
                } else {
                    alert("问卷添加失败");
                }
            },
            error: function (message) {
                alert("问卷添加请求失败");
            }
        })
    })
})