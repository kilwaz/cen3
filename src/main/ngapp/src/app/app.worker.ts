/// <reference lib="webworker" />

addEventListener("message", ({data}) => {
  let files: FileList = data;

  Array.from(files).forEach(file => {
    console.log("Uploading " + file.name + " (" + file.size + " bytes)");

    let fileReader: FileReader = new FileReader();
    fileReader.onload = function () {
      postMessage(fileReader.result);

      let _fileUploadWS: WebSocket = new WebSocket("ws://localhost:4568/upload");
      let _arrayBuffer: ArrayBuffer = null;

      if (fileReader.result instanceof ArrayBuffer) {
        _arrayBuffer = fileReader.result;
      }

      _fileUploadWS.onopen = function (evt) {
        _fileUploadWS.send(file.name);
      };

      _fileUploadWS.onmessage = function (evt) {
        let chunkSize: number = 30000;
        let i: number;
        let j: number;
        for (i = 0, j = _arrayBuffer.byteLength; i < j; i += chunkSize) {
          _fileUploadWS.send(_arrayBuffer.slice(i, i + chunkSize));
        }

        _fileUploadWS.close(1000, "File Complete");
      };
      _fileUploadWS.onclose = function () {
        console.log("Upload connection closed");
      };
    };

    fileReader.onerror = function () {
      console.log(fileReader.error);
    };

    fileReader.readAsArrayBuffer(file);
  });
});
