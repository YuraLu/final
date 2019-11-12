$(function() {
    $(document).on('change', ':checkbox', function () {
        if (this.checked) {
            this.value = '1';
        } else {
            this.value = '0';
        }
    });
});