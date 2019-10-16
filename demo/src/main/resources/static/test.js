


$(function(){

   $("#employeePage").click(function(){
        fetchData();
   });

   $("#addUser").click(function(){
        addForm();
   });
});

function fetchData(){

    $.ajax({
        type: "GET",
        url: "/getEmployees",
        success: function(data){
            $('.main-content').html(data);
        }
    });
}

function addForm(){

    $.ajax({
        type: "GET",
        url: "/addEmployee",
        success: function(data){
            $('.main-content').html(data);
        }
    });
}

function editForm(id){

    $.ajax({
        type: "GET",
        url: "/editUser/"+id,
        success: function(data){
            $('.main-content').html(data);
        }
    });
}

function deleteForm(id){

console.log(id);
    $.ajax({
            type: "POST",
            url: "/deleteUser/"+id,
            success: function(data){
                alert('usunieto usera');
                fetchData();
            },
            error: function(data, xhr, thrown){
                                   alert(thrown);

                               }
        });
}
