/// <reference lib="webworker" />

addEventListener('message', ({data}) => {
  const files: FileList = data;

  Array.from(files).forEach(file => {
    console.log('Uploading ' + file.name + ' (' + file.size + ' bytes)');

    const fileReader: FileReader = new FileReader();
    fileReader.onload = () => {
      postMessage(fileReader.result);

      const fileUploadWS: WebSocket = new WebSocket('ws://localhost:4568/upload');
      let arrayBuffer: ArrayBuffer = null;

      if (fileReader.result instanceof ArrayBuffer) {
        arrayBuffer = fileReader.result;
      }

      fileUploadWS.onopen = evt => {
        fileUploadWS.send(file.name);
      };

      fileUploadWS.onmessage = evt => {
        const chunkSize = 30000;
        let i: number;
        let j: number;
        for (i = 0, j = arrayBuffer.byteLength; i < j; i += chunkSize) {
          fileUploadWS.send(arrayBuffer.slice(i, i + chunkSize));
        }

        fileUploadWS.close(1000, 'File Complete');
      };
      fileUploadWS.onclose = () => {
        console.log('Upload connection closed');
      };
    };

    fileReader.onerror = () => {
      console.log(fileReader.error);
    };

    fileReader.readAsArrayBuffer(file);
  });
});
