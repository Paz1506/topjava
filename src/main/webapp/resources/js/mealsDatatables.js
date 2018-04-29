var ajaxMealUrl = "ajax/meals";
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $("#mealDatatable").DataTable({
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

function addMeal() {
    $("#addMealModal").find(":input").val(""); //Обнуляем значения полей при вызове мод. окна
    $("#addMealModal").modal();
}

function saveMeal() {
    var form = $("#detailsForm");
    $.ajax({
        type: "POST",
        url: "ajax/meals",
        data: form.serialize(),
        success: function () {
            $("#addMealModal").modal("hide");
            updateTable();
            successNoty("Saved");
        }
    });
}

function updateTable() {
    $.get(ajaxMealUrl, function (data) {
        datatableApi.clear().rows.add(data).draw();
    });
    filterMeal();
}

function filterMeal() {
    var filterForm = $("#filterForm");
    $.ajax({
        type: "GET",
        url: "ajax/meals/filter",
        data: filterForm.serialize(),
        success: function (data) {
            datatableApi.clear().rows.add(data).draw();
            // $("#addMealModal").modal("hide");
            // updateTable();
            // successNoty("Saved");
        }
    });
}

function resetFilter(){
    var filterForm = $("#filterForm");
    filterForm.find(":input").val("");
    // datatableApi.clear();
    updateTable();
}