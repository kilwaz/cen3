/// <reference lib="webworker" />

addEventListener('message', ({ data }) => {
  let response = `worker refghfbhdgbsponse to ${data}`;
  console.log("This was running now");
  postMessage(response);
});
