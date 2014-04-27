//Event
$(document).ready(function()
{
    document.loadSidebar();
});
//Account
document.popLogin = function()
{
  
    $("#cover").fadeIn(function ()
    {
        $("#form-signin-container").fadeIn();
    });
};
document.hideLogin = function ()
{
    $("#form-signin-container").fadeOut(function ()
    {
       $("#cover").fadeOut();
    });
   
   
};
document.login = function ()
{

    $.ajax({
        url: "Login.exec",
        cache: false,
        type: "POST",
        dataType :"json",
        data: {
            name: $("#form-signin-username").val(),
            password: $("#form-signin-password").val()
        },
        success: function (data)
        {
            $("#form-signin-notice").show();
            $("#form-signin-notice").removeClass();
            switch (data.status)
            {
                case 0:

                    $("#form-signin-notice").addClass("alert alert-success");
                    $("#form-signin-notice").text(data.text);
                    document.receiveUserData();
                    document.hideLogin();
                    break;
                case 1:

                    $("#form-signin-notice").addClass("alert alert-info");
                    $("#form-signin-notice").text(data.text);
                    break;

                case 2:

                    $("#form-signin-notice").addClass("alert alert-warning");
                    $("#form-signin-notice").text(data.text);
                    break;

                default:
                    $("#form-signin-notice").addClass("alert alert-danger");
                    $("#form-signin-notice").text(data.text);
                    break;
            }
            document.loadSidebar();
        }

    });
};

document.logout = function()
{
    $.removeCookie('identification');
    $.removeCookie('varify');
    $("#signin-block").show();
    $("#signed-block").hide();
};

document.receiveUserData = function ()
{
    $.ajax({
        url: "ReceiveUserData.exec",
        success: function (data)
        {
            $("#signin-block").hide();
            $("#signed-block").show();
        }

    });
};
//Content Loader
document.loadContent = function (url)
{
    $.ajax({
        url: url,
        success: function (data)
        {
            $("#content").html(data);
          //  document.contentInit();
        }

    });
};

document.loadSidebar = function ()
{
    $.ajax({
        url: "Sidebar.exec",
        dataType: "json",
        cache:false,
        success: function (data)
        {
            
            $("#sidebar-content").empty();
            $(data).each(function ()
            {
                var li = $("<li></li>");
                li.addClass("sidebar-child");
                var a = $("<a></a>");
                a.attr("href", "#");
                var i = $("<i></i>");
                i.addClass("icon");
                i.addClass(this.iconName);
                var span = $("<span></span>");
                span.text(this.title);
                li.append(a);
                a.append(i);
                a.append(span);
                li.attr("onclick", "document.loadContent('" + this.url + "');");
                
                $("#sidebar-content").append(li);
            });
            $(".sidebar-child")[0].click();
        }

    });
}