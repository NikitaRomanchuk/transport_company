function addDeleteAction(element) {
    let button = element;
    let path_id = button.data('id');
    $.ajax({
            url: '/admin/paths',
            method: 'DELETE',
            data: {'pathId': path_id},
            success: function () {
                console.log(button.parent().parent());
                button.parent().parent().remove();
            },
            error: msg => console.log(msg)
        }
    )
}

$(function () {
    $('.delete-button').on("click", function () {
        addDeleteAction($(this));
    });
    $('#add-button').on("click", function () {
        $.ajax({
                url: '/admin/paths',
                method: 'POST',
                data:
                    {
                        'cityFrom': $('#from-input').val(),
                        'cityTo': $('#to-input').val(),
                        'distance': $('#dist').val()
                },
                success: function (resp){
                    console.log(resp)
                    $('#path-table').append($('<tr>\n' +
                        '            <td>' + resp['cityFromName'] + '</td>\n' +
                        '            <td>' + resp['cityToName'] + '</td>\n' +
                        '            <td>' + resp['distance'] + '</td>\n' +
                        '            <td><button data-id="' + resp['id'] + '" class="btn btn-danger delete-button">Delete</button></td>\n' +
                        '        </tr>'));
                    $('.delete-button').on("click", function () {
                        addDeleteAction($(this));
                    });
                },
                error: msg => console.log(msg)
            }
        )
    });
});