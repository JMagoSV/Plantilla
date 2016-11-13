$(document).ready(function() {
    $.fn.initBootTable = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('check.bs.table').on('check.bs.table', function (e, row) {
            consRole([{name : 'codiAlumPara', value : row.id.trim()}]);
        });
        return false;
    };
    $.fn.initBootTableRole = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('load-success.bs.table').on('load-success.bs.table', function (e, row) {
            $("[data-id='chck']").bootstrapToggle();
        }).
        unbind('page-change.bs.table').on('page-change.bs.table', function (e, row) {
            $("[data-id='chck']").bootstrapToggle();
        });
        return false;
    };
    $('#ModaRole').on('show.bs.modal', function() {
        INIT_OBJE_ROLE();
    });
    $('#ModaRole').on('hide.bs.modal', function() {
        $("#TablUsua").bootstrapTable('uncheckAll');
    });

    
    INIT_OBJE_ROLE();
});

function INIT_OBJE_ROLE()
{
    $("#TablUsua").initBootTable();
    INIT_OBJE_MODA_ROLE();
}

function INIT_OBJE_MODA_ROLE()
{
    $("#FormRole\\:btonElim").confirmation({container: '#FormRole'});
    $("#FormRole\\:TablUsuaRole").initBootTableRole();
    $("[data-id='chck']").bootstrapToggle();
}