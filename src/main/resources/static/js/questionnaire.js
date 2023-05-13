/**
 * 将 id 为 sID 的表单结果转为 json 格式
 * @param sID 表单 id
 * @returns {any}
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

function evaluationDivInit(oDiv, min, max, step, value) {
    let oRange = $(oDiv).find(":in-range");
    let oValue = $(oDiv).find(".evaluation-value");
    let oShow = $(oDiv).find(".evaluation-show");
    oRange.attr("min", min);
    oRange.attr("max", max);
    oRange.attr("step", step);
    oRange.on("input property-change", (event) => {
        oValue.text(oRange.val());
    }).val(value);
    oValue.text(oRange.val());
}

$(function () {
    $(".evaluation-curriculum-div").each(function (index) {
        evaluationDivInit(this, 0, 10, 1, 5);
    })
    $(".evaluation-teacher-div").each(function (index) {
        evaluationDivInit(this, 0, 10, 1, 5);

        let oRange = $(this).find(":in-range");

    })
    $(".evaluation-self-div").each(function (index) {
        evaluationDivInit(this, 5, 100, 5, 5);
    })

    $("#submit").click(function () {
        let oProfessionalInput = $("#questionnaire-form").find("#self-professional");
        if (oProfessionalInput.val().trim().length === 0) {
            oProfessionalInput.val("");
            alert("请输入自己的专业");
            window.location.href = "#self-professional";
            return;
        }
        console.log(formJson("questionnaire-form"));
    })
    $("input[type='range']").each(function (index) {
        let min = $(this).attr("min");
        let max = $(this).attr("max");
        $(this).bind("input", function (e) {
            $(this).css('background', 'linear-gradient(to right, white, #05FA9C ' + ($(this).val() - min) / (max - min) * 100 + '%, white 0%, white)');
        });
        $(this).css('background', 'linear-gradient(to right, white, #05FA9C ' + ($(this).val() - min) / (max - min) * 100 + '%, white 0%, white)');
    });
})