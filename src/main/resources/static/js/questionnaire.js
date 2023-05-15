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
    let jsonStr = "{";
    $(sClass).each(function (){
        let oRange = $(this).find(":in-range");
        jsonStr += '"' + oRange.attr("name") + '":' + oRange.val() + ',';
    });
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
    const diameter = 25;
    oRange.on("input property-change", () => {
        let leftOffset = oRange.position().left + diameter / 2 + oRange.width() * (oRange.val() - min) / (max - min) * (1 - diameter / oRange.width()) - oShow.width() / 2;
        let topOffset = oRange.position().top - oShow.height() - 12;
        oShow.css("left", leftOffset + "px");
        oShow.css("top", topOffset  + "px");
        oValue.text(oRange.val());
        oRange.css('background', 'linear-gradient(to right, white, #05FA9C ' + (oRange.val() - min) / (max - min) * 100 + '%, white 0%, white)');
        oShow.show();
    }).val(value);
    oRange.focusout(function () {
        oShow.hide();
    })
    oValue.text(oRange.val());
    oRange.css('background', 'linear-gradient(to right, white, #05FA9C ' + (oRange.val() - min) / (max - min) * 100 + '%, white 0%, white)');
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
        let oProfessionalInput = $("#questionnaire-form").find("#self-professional");
        if (oProfessionalInput.val().trim().length === 0) {
            oProfessionalInput.val("");
            alert("请输入自己的专业");
            window.location.href = "#self-professional";
            return;
        }
        console.log(formJson("questionnaire-form"));
    })
})