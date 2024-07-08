![Cover](images/mainreadme-cover.svg)
# Sample Manager

Sample Manager is a Java program designed to streamline the cataloging process of samples stored in refrigerators (or not), with built-in barcode generation for efficient tracking.

## Features

- **Sample Cataloging**: Easily catalog samples with detailed information such as description, expiration date, and hazardous status.
- **Barcode Generation**: Generate unique barcodes for each sample, facilitating quick identification and tracking.
- **Efficient Management**: Streamline inventory management with intuitive tools for adding, updating, and removing samples.
- **Print Functionality**: Seamlessly print generated barcodes for physical labeling and easy identification in the laboratory.
- **Sample Searching:** Quickly search for samples using keywords or barcodes/barcode prefixes to locate them in the catalog.
- **Identification (Laboratory) Addition:** Add a unique laboratory identifier as a prefix to the barcode for enhanced organization and traceability.
- **Column Configuration:** Each sample attribute (Description, Hazard Type, Execution Date, Expiration Date, Identifier, and Barcode) is displayed in its own column within the table for easy viewing and sorting.
- **Sample Details Dialog:** The creation and editing of samples are now managed through comprehensive dialogs that ensure all necessary information is captured accurately.

## Use from source code

1. Clone the repository to your local machine.
2. Open the project in your preferred Java IDE.
3. Run the `Main` class to launch the application.
4. Follow the on-screen instructions to start cataloging and managing samples, or visit the user-manual.

## Bugs & Opinion
If you encounter any bugs or issues with the program, please notify me. Your feedback is invaluable in improving Sample Manager.

## Dependencies

- [ZXing](https://github.com/zxing/zxing): Library for barcode generation.
- [JavaFX](https://openjfx.io/): UI toolkit for Java applications.

## License

This project is licensed under the BSD 3-Clause License - see the [LICENSE](LICENSE) file for details.
