/// <reference lib="webworker" />

addEventListener("message", ({data}) => {
  let files: FileList = data;

  Array.from(files).forEach(file => {
    console.log("Uploading " + file.name + " (" + file.size + " bytes)");

    let fileReader: FileReader = new FileReader();
    fileReader.onload = function () {
      postMessage(fileReader.result);
    };

    fileReader.onerror = function () {
      console.log(fileReader.error);
    };

    fileReader.readAsArrayBuffer(file);
  });
});
