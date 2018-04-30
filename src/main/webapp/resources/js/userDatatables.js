var ajaxUrl = "ajax/admin/users/";
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled"
            },
            {
                "data": "registered"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();
});


//изменение состояния чекбокса
function checkEnabled(user_id) {
    var name11 = "cb_"+user_id;
    var name22 = "tr_"+user_id;
    var enabled = $('#'+name11).prop('checked');
    if (enabled){
        $('#'+name11).prop('checked', true);
        $('#'+name22).css("background-color", "transparent");
    } else {
        $('#'+name11).prop('checked', false);
        $('#'+name22).css("background-color", "red");
    }
    // alert("User " + user_id +
    //     "/ stat: " + $('#'+name11).prop('checked'));
    //     // "/ stat: " + $('#name').is(':checked'));
    $.ajax({
        url: ajaxUrl + user_id,
        type: "POST",
        data:{
            enabled: enabled
        },
        success: function () {
            successNoty("Enabled update!");
        }
    });


}