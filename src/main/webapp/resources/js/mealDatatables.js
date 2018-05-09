var ajaxUrl = "ajax/profile/meals/";
var datatableApi;

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false,
                "render": renderEditBtn
            },
            {
                "defaultContent": "Delete",
                "orderable": false,
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            if (data.calories > 2000) {
                $(row).css("color", "red");
            } else {
                $(row).css("color", "green");
            }
        },
        "initComplete": makeEditable
    });
    makeEditable();

    //$.datetimepicker.setLocale(localeCode);

//  http://xdsoft.net/jqplugins/datetimepicker/
    var startDate = $('#startDate');
    var endDate = $('#endDate');
    startDate.datetimepicker({
        timepicker: false,
        format: 'Y-m-d',
        // formatDate: 'Y-m-d',
        // onShow: function (ct) {
        //     this.setOptions({
        //         maxDate: endDate.val() ? endDate.val() : false
        //     })
        // }
    });
    endDate.datetimepicker({
        timepicker: false,
        format: 'Y-m-d'
        // formatDate: 'Y-m-d',
        // onShow: function (ct) {
        //     this.setOptions({
        //         minDate: startDate.val() ? startDate.val() : false
        //     })
        // }
    });

    $('#startTime, #endTime').datetimepicker({
        datepicker: false,
        format: 'H:i'
    });

    //Разобраться с форматом
    $('#dateTime').datetimepicker({
        //format: 'Y-m-d H:i'
        format:      'Y-m-d\\TH:i:s'
        // formatTime:  'HH:mm',
        // formatDate:  'YYYY-MM-DD'
    });
});