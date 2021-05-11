function addDeleteAction(element) {
    let button = element;
    let passage_id = button.data('id');
    $.ajax({
            url: '/admin/passages',
            method: 'DELETE',
            data: {'passageId': passage_id},
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
                url: '/admin/passages',
                method: 'POST',
                data:
                    {
                        'cityFrom': $('#from-input').val(),
                        'cityTo': $('#to-input').val(),
                        'cost': $('#cost').val(),
                        'start': $('#start').val()
                    },
                success: function (resp){
                    console.log(resp)
                    $('#passage-table').append($('<tr>\n' +
                        '            <td>' + resp['cityFromName'] + '</td>\n' +
                        '            <td>' + resp['cityToName'] + '</td>\n' +
                        '            <td>' + resp['cost'] + '</td>\n' +
                        '            <td>' + resp['start'] + '</td>\n' +
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