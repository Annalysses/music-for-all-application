"use strict";

$(document).ready(function () {

    /* Perform search using Ajax. */
    $("#search-form").on("submit", function () {

        if ($("#query").val() == "") {
            return false;
        }
        search();
        return false;
        /* Prevent default */
    });

    /* Handle the scroll-to-top link. */
    $("#scroll-to-top").on("click", function (event) {
        window.scrollTo(0, 0);
        return false;
    });

});
/* end $(document).ready() */

function search() {

    var query = $("#query").val();
    if (query.length > 40) {
        query = query.substr(0, 40); //Trimming long line
    }
    var category = $("#search-form select[name=category] option:selected").val();
    console.log("Search query: " + query);
    console.log("Category: " + category);

    /* Empty the search results table. */
    $("#results tr:gt(0)").remove();

    $.ajax({
        type: "GET",
        url: "/api/search/",
        data: {
            query: query,
            category: category
        },
        dataType: "json"
    }).fail(function (xhr, status, errorThrown) {
        $("#status").text("Service is currently unavailable");
        console.error("There was a problem");
        console.log("Error: " + errorThrown);
        console.log("Status: " + status);
    }).done(function (data) {
        console.log(data);
        populateResultsTable(data);
    });
}

function populateResultsTable(data) {

    $.each(data, function (i, item) {
        $("#results").append($("<tr>").append(
            $("<td>").text(item.id),
            $("<td>").text(item.name),
            $("<td>").text(item.location)
        ));
    });
}

function ajaxAddSong(AddId) {
    var header = $("meta[name='_csrf_header']").attr("content");
    var token = $("meta[name='_csrf']").attr("content");
    $.ajax({
        type: "POST",
        url: "/addSong",
        data: "songId=" + AddId,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        success: function () {
            console.log("Request to add was submitted successfully");
        },
        error: function () {
            alert("Error while request..");
        }
    });
}