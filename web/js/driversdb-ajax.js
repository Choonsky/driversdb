
/* JQuery GET shorthand function */

/*

$(document).ready(function() {
    $('#userName').blur(function(event) {
        var name = $('#userName').val();
        $.get('DbSearch', {
            userName : name
        }, function(responseText) {
            $('#ajaxGetUserServletResponse').text(responseText);
        });
    });
});

*/

/* JQuery AJAX function */

$(document).ready(function() {
    $('#userName').blur(function() {
        $.ajax({
            url : 'DbSearch',
            data : {
                userName : $('#userName').val()
            },
            success : function(responseText) {
                $('#ajaxDbSearchResponse').text(responseText);
            }
        });
    });
});

jQuery(function ($) {
    var $inputs = $('input[name=familyName],input[name=firstName],input[name=secondName]');
    $inputs.on('input', function () {
        // Set the required property of the other input to false if this input is not empty.
        $inputs.not(this).prop('required', !$(this).val().length);
    });
});

$('.textField').keypress(function (e) {
    var regex = new RegExp("^[а-яА-Я]+$");
    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
    if (regex.test(str)) {
        return true;
    }
    else {
        e.preventDefault();
        alert('Пожалуйста, вводите только буквы!');
        return false;
    }
});

$('.textNumField').keypress(function (e) {
    var regex = new RegExp("^[а-яА-Я0-9]+$");
    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
    if (regex.test(str)) {
        return true;
    }
    else {
        e.preventDefault();
        alert('Пожалуйста, вводите только буквы и цифры!');
        return false;
    }
});