$(function () { $("[data-toggle='tooltip']").tooltip(); });

$('#prefForm').submit(function(e){
    e.preventDefault();
    $.ajax({
        type: 'POST',
        url: '/savePrefs',
        contentType: 'application/json',
        data: JSON.stringify({
            initialDirectory: $('#initial').val(),
            maxNestingLevel:  $('#nesting').val(),
            showHiddenFiles:  $('#hidden').prop("checked"),
            showFoldersOnly:  $('#folders').prop("checked")
        }),
        success: function() {
            $('#message').show();
            setTimeout('location.replace("/")',3000)
        }
    });
})

var backToDefaults = function() {
    $('#initial').val("C:/");
    $('#nesting').val("30");
    $('#hidden').prop("checked",false);
    $('#folders').prop("checked",false);
    $('#prefForm').submit();
}