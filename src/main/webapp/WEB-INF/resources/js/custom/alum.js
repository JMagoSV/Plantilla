$(document).ready(function() {
    $.fn.initBootTable = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('check.bs.table').on('check.bs.table', function (e, row) {
            consAlum([{name : 'codiAlumPara', value : row.id.trim()}]);
        });
        return false;
    };
    
    INIT_OBJE_ALUM();
});

function INIT_OBJE_ALUM()
{
    $("#TablAlum").initBootTable();
    $("#FormAlum\\:btonElim").confirmation({container: '#FormAlum'});
}