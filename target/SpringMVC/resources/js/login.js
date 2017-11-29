$(document).ready(function(){
   var height = window.innerHeight;
   $(".registrationbox").css('height',$(document).height());
});

function onClickAjax(contextPath){
    var username = $("#username_login").val();
    var password = $("#password_login").val();

    var data = 'username='+username+'&password='+password;

    $.ajax({
        type : "GET",
        contentType : "application/json",
        url : contextPath + "/getSearchResult1",
        data : data,
        dataType : 'json',
        timeout : 100000,
        success : function(data) {
            var s = "";
            $.each(data, function(k, v) {
                s += "<br>K: " + k + " --- v: " + v;
                if(v == 0){
                    s = "<p>No user found!</p>";
                }
            });

            $("#showdiv").html(s);
        },
        error: function (xhr, ajaxOptions, thrownError) {
            console.log(thrownError);
        }
    });
}