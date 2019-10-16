

$(document).ready(function(){
    $('.nBtn, .table .eBtn').click(function(event){

        event.preventDefault(); //przyciski dzialaja jak linki, przenosza na inna strone, ten zapis zatrzymuje ich dzialanie

        var href = $(this).attr('href');

        console.log();
        if($(this).text()=='Edit'){
        $.get(href, function(user, status){
            $('.myForm #username').val(user.username);
            $('.myForm #password').val(user.password);
            $('.saveCreateButton').val('Save');
        });
        }else{
             $('.myForm #username').val('');
             $('.myForm #password').val('');
             $('.saveCreateButton').val('Create');
        }
        $('.myForm #exampleModal').modal(); //wyswietla modal
    });
})