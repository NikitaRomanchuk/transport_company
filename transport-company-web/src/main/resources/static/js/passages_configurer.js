$(function () {
    $('#request-button').on('click', function () {
        $('#passage-table').html('');
        $.ajax({
                url: '/passages?from=' + $('#from').val() + '&to=' + $('#to').val(),
                method: 'GET',
                success: function (resp){
                    console.log(resp)
                    for(let i = 0; i < resp.length; i++) {
                        let pass = resp[i];
                        let elem = $('<tr id="'+ pass['id'] + '">\n' +
                            '            <td>' + pass['cityFromName'] + '</td>\n' +
                            '            <td>' + pass['cityToName'] + '</td>\n' +
                            '            <td>' + pass['cost'] + '</td>' +
                            '        </tr>')
                        elem.on('click', function () {
                            location.href = '/passages/' + pass['id'];
                        })
                        $('#passage-table').append(elem);
                    }
                },
                error: msg => console.log(msg)
            }
        );
    });
});