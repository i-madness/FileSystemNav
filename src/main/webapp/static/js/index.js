// linking folders in table
$('body').on('click','.folderLink', function() {
    currentDir = $(this).children('.tdName').text();
    $('#pathInput').val($('#pathInput').val()+currentDir+'/')
    $('#idForm').submit();
});

// linking parent folder
$('body').on('click','#parentBtn', function(e) {
    e.preventDefault();
    var dir = $('#pathInput').val();
    var par = $('#parentUp').text();
    if (dir.length > 3) {
        /*var pos = dir.length - 2;
         while (dir[pos] != '/')
         pos--;
         $('#pathInput').val(dir.substr(0, pos+1))*/
        $('#pathInput').val(par[par.length-1]=='/' ? par : par + '/')
        $('#idForm').submit();
    }
});

// linking files
$('body').on('click','.fileLink', function() {
    currentDir = $('#pathInput').val();
    $.get('/file/'+currentDir.split("/").join("-_-")+$(this).children('.tdName').text().split(".").join('-__-'), function(ReadableFile) {
        $('#fileName').text(ReadableFile.name);
        $('#fileContent').empty();
        if (ReadableFile.content == null)
            $('#fileContent').text('Файл не может быть отображен')
        else {
            for (var i = 0; i < ReadableFile.content.length; i++)
                $('#fileContent').append('<p>' + ReadableFile.content[i] + '</p>')
        }
    })
        .fail(function(){
            $('#fileContent').text('Файл не может быть отображен')
        });
    $("#fileView").modal()
});

$('#idForm').submit(function(e) {
    currentDir = $('#pathInput').val();
    if (currentDir[currentDir.length-1]!='/')
        $('#pathInput').text(currentDir = currentDir.concat('/'))
    $.get('/dir/' + currentDir.split("/").join("-_-").split(".").join('-__-'), function(directory) {
        if (directory!==undefined && directory.name.indexOf('(forbidden!)')==-1) {
            $('#folderResponse').text(directory.name);
            $('#parentUp').text(directory.parent == null ? '(корневая директория)' : directory.parent.split("\\").join("/"))
            if (directory.parent == null)
                $('#dropdownParent').addClass('disabled');
            else if ($('#dropdownParent').hasClass('disabled'))
                $('#dropdownParent').removeClass('disabled');
            $('#parentMenu').empty();
            if (currentDir.length > 3) {
                var pos = currentDir.length - 2;
                var parents = [];
                while (pos >= 0) {
                    if (currentDir[pos] == "/")
                        parents.push(currentDir.substr(0, pos + 1));
                    pos--;
                }

                for (var i = 0; i < parents.length; i++)
                    $('#parentMenu').append('<li><a href="#" class="menu-item">'.concat(parents[i]).concat('</a></li>'))
                $('body').on('click', '.menu-item', function () {
                    $('#parentUp').text($(this).html())
                    $('#parentBtn').click()
                })
            }
            $('#folder-view').empty();
            $('#folder-view').append('<thead><td>/</td><td>Имя</td><td>Тип</td></thead>')
            if (directory.files !== undefined && directory.files != null) {
                for (var i = 0; i < directory.files.length; i++) {
                    $('#folder-view').append('<tr class="fileLink"><td align="center"><span class="glyphicon glyphicon-file"></span></td><td class="tdName">' + directory.files[i] + '</td><td align="center">Файл</td>')
                }
            }
            if (directory.subdirectories !== undefined && directory.subdirectories != null) {
                for (var i = 0; i < directory.subdirectories.length; i++) {
                    $('#folder-view').append('<tr class="folderLink"><td align="center"><span class="glyphicon glyphicon-folder-close"></span></td><td class="tdName">' + directory.subdirectories[i] + '</td><td align="center">Папка</td>')
                }
            }
        } else {
            var pos = currentDir.length - 2;
            while (pos>=0 && currentDir[pos] != "/") {
                pos--;
            }
            alert('Директория не может быть показана!')
            $('#pathInput').val(currentDir.substr(0, pos + 1))

        }
    });
    e.preventDefault(); // prevent form submit
});

// submitting data after page loads
$('#idForm').submit();
$(function () { $("[data-toggle='tooltip']").tooltip(); });