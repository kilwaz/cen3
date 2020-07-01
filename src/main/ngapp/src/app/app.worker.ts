/// <reference lib="webworker" />

addEventListener('message', ({data}) => {
  console.log('Anything here?');

  const files: FileList = data;

  Array.from(files).forEach(file => {
    console.log('Uploading ' + file.name + ' (' + file.size + ' bytes)');

    const fileReader: FileReader = new FileReader();
    fileReader.onload = () => {
      postMessage(fileReader.result);

      const _fileUploadWS: WebSocket = new WebSocket('ws://localhost:4568/upload');
      let _arrayBuffer: ArrayBuffer = null;

      if (fileReader.result instanceof ArrayBuffer) {
        _arrayBuffer = fileReader.result;
      }

      _fileUploadWS.onopen = evt => {
        _fileUploadWS.send(file.name);
      };

      _fileUploadWS.onmessage = evt => {
        const chunkSize = 30000;
        let i: number;
        let j: number;
        for (i = 0, j = _arrayBuffer.byteLength; i < j; i += chunkSize) {
          _fileUploadWS.send(_arrayBuffer.slice(i, i + chunkSize));
        }

        _fileUploadWS.close(1000, 'File Complete');
      };
      _fileUploadWS.onclose = () => {
        console.log('Upload connection closed');
      };
    };

    fileReader.onerror = () => {
      console.log(fileReader.error);
    };

    fileReader.readAsArrayBuffer(file);
  });
});
