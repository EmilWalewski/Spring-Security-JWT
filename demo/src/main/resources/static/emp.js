
$(function(){
    $("#submitEmployeeForm").submit(function(e){
        e.preventDefault();

        var form  = $("#submitEmployeeForm");

        var data = {};

        $.each(this, function(i, v){
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        saveData(form, data);
    });
});

function saveData(form, data){

        //alert(form.serialize());

    $.ajax({
        contentType:"application/json; charset=utf-8",
        type:form.attr("method"),
        url:form.attr("action"),
        dataType:"json",
        data:JSON.stringify(data),
        success: function(data){
                     alert(data.message);
                     $.ajax({
                         type: "GET",
                         url: "/getEmployees",
                         success: function(data){
                                     $('.main-content').html(data);
                          }
                     });
        }


    });
}