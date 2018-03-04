package currencyviewer.enums

enum CurrencyCode {
    USD("R01235"),
    EUR("R01239")

    String value

    CurrencyCode(String value) {
        this.value = value;
    }
}