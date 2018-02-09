$(function () {
    $('.simple .sendBtn').bind("click", sendSimpleMail);
    $('.html .sendBtn').bind("click", sendHtmlMail);
    $('.attachment .sendBtn').bind("click", sendAttachmentsMail);
});

function sendSimpleMail() {
    var param = {
        "to": $('.simple .to').val(),
        "subject": $('.simple .subject').val(),
        "content": $('.simple .content').val()
    };
    $.post("sendSimpleEmail", param, function (data) {
        console.log(data);
    });
}

function sendHtmlMail() {
    var param = {
        "to": $('.html .to').val(),
        "subject": $('.html .subject').val(),
        "content": $('.html .content').val()
    };
    $.post("sendHtmlEmail", param, function (data) {
        console.log(data);
    });
}

function sendAttachmentsMail() {
    var param = {
        "to": $('.attachment .to').val(),
        "subject": $('.attachment .subject').val(),
        "content": $('.attachment .content').val()
    };
    $.post("sendAttachmentsEmail", param, function (data) {
        console.log(data);
    });
}