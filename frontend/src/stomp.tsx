import { useState } from "react";
import { useStompClient, useSubscription } from "react-stomp-hooks";

export default function Tmp() {
  const [lastMessage, setLastMessage] = useState("No message received yet");
  useSubscription("/topic/test", (message) => setLastMessage(message.body));

  const stompClient = useStompClient();

  const sendMessage = () => {
    if (stompClient) {
      stompClient.publish({
        destination: "/app/echo",
        body: `${Date.now()}`,
      });
    } 
  };

  console.log(lastMessage);

  return <button onClick={sendMessage}>Send Message</button>;
}