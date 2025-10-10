function setupInputValidation() {
    const yInput = document.querySelector('.YChange');

    const validateInput = (input) => {
        input.addEventListener('input', function () {

            if (this.value.length > 5) {
                this.value = this.value.substring(0, 5);
                return;
            }

            this.value = this.value.replace(/[^0-9.,-]/g, '');
            this.value = this.value.replace(/,/g, '.');

            if ((this.value.match(/\./g) || []).length > 1) {
                this.value = this.value.substring(0, this.value.lastIndexOf('.'));
            }

            if (this.value.indexOf('-') > 0) {
                this.value = this.value.replace(/-/g, '');
            }

            if (this.value.length > 1 && this.value.includes('-')) {
                this.value = this.value.replace(/-/g, '');
                this.value = '-' + this.value;
            }
        });
    };
    validateInput(yInput);
}
setupInputValidation();
