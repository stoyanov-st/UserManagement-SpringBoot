function radioSubmit() {
    var filter = $("input[name=filter]:checked").val();
    var token = $('#_csrf').attr('content');
    var header = $('#_csrf_header').attr('content');
    $.ajax({
        type: 'POST',
        url: '/admin/filter',
        beforeSend: function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        contentType: 'text/plain',
        data: filter,
        success: function (result) {
           $("body").html(result);
        },
        error: function (error) {
            console.log(error);
        }
    });
}