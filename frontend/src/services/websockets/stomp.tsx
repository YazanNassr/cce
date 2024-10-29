// import { useState } from "react";
// import { useStompClient, useSubscription } from "react-stomp-hooks";
// import {useWorkspaceContext} from "../../hooks/WorkspaceContext.tsx";
//
// export default function TrackFile() {
//   const {workspace, setWorkspace} = useWorkspaceContext()
//
//   useSubscription("/topic/", (message) => setLastMessage(message.body));
//
//   const stompClient = useStompClient();
//
//   const sendMessage = () => {
//     if (stompClient) {
//       stompClient.publish({
//         destination: "/app/echo",
//         body: `${Date.now()}`,
//       });
//     }
//   };
//
//   console.log(lastMessage);
//
//   return <button onClick={sendMessage}>Send Message</button>;
// }