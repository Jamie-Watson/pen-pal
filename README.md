# Pen-Pal: Reading Aid

## Running

To install and run the project, follow these steps:

1. Click on the green "Code" button on the upper right-hand corner.
2. Download the ZIP file.
3. Extract the file.
4. Navigate to the directory where the folder is stored:
    ```sh
    cd file-directory
    ```
5. Either run the code using a preferred IDE or through the command line:

    **Compile Java files:**
    ```sh
    javac -cp "libs/*" -d . *.java
    ```

    **Run the application:**
    ```sh
    java -cp ".;libs/*" TextToSpeechClass
    ```

6. Note that this project will automatically include the TTS APIs required to run.
