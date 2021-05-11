$(function () {
    $('#book-btn').on('click', function () {
        let elem = $(this);
        if(!$(this).hasClass('disabled')){
            $.ajax({
                url:'/ticket',
                method:'POST',
                data:{
                    passageId:elem.data('id')
                },
                success: function (msg) {
                    elem.addClass('disabled');
                    let pay_btn = $('#pay-btn');
                    pay_btn.removeClass('disabled');
                    pay_btn.data('id', msg['id']);
                }
            })
        }
    });
    $('#pay-btn').on('click', function () {
        let elem = $(this);
        if(!$(this).hasClass('disabled')){
            $.ajax({
                url:'/ticket',
                method:'POST',
                data:{
                    ticketId:elem.data('id')
                },
                success: function () {
                    elem.addClass('disabled');
                }
            })
        }
    });
})