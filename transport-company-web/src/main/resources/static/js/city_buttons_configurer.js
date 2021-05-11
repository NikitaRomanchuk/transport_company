function addDeleteAction(element) {
    let button = element;
    let city_id = button.data('id');
    $.ajax({
            url: '/admin/cities',
            method: 'DELETE',
            data: {'cityId': city_id},
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
                url: '/admin/cities',
                method: 'POST',
                data: {'name': $('#name-input').val()},
                success: function (resp){
                    console.log(resp)
                    $('#city-table').append($('<tr>\n' +
                        '            <td>' + resp['name'] + '</td>\n' +
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