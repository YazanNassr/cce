import Editor from '@monaco-editor/react'
import {useWorkspaceContext} from "../hooks/WorkspaceContext.tsx";
import {ReplacementModification} from "../types/types.ts";
import {IMessage, useStompClient, useSubscription} from "react-stomp-hooks";

export default function CodeEditor() {
    const {workspace, setWorkspace} = useWorkspaceContext()
    const stompClient = useStompClient();
    const token = (sessionStorage.getItem("jwt").slice(6));

    const sendMessage = (modification : ReplacementModification) => {
        if (stompClient) {
            stompClient.publish({
                destination: "/modify",
                body: JSON.stringify(modification),
                headers: {
                    Authorization: token
                }
            })
        }
    }

    function modifyFile(modification: ReplacementModification) {
        const {start, end, newVal} = modification;
        const sourceCode = workspace.activeFile.sourceCode;
        const newText = sourceCode.slice(0, start) + newVal + sourceCode.slice(end);
        setWorkspace({
            ...workspace,
            activeFile: {...workspace.activeFile, sourceCode: newText},
        })
    }

    useSubscription("/topic/modify", (message: IMessage) => {
        const modification : ReplacementModification = JSON.parse(message.body);
        modifyFile(modification)
    }, {
        Authorization: token
    })

    function handleEditorChange(value, event) {
        if (!value) return;

        const modifications = event.changes.map(c => ({
            projectId: workspace.project.id,
            filePath: workspace.activeFile.fileName,
            start: c.rangeOffset,
            end: c.rangeOffset + c.rangeLength,
            newVal: c.text
        }));

        sendMessage(modifications[0]);
    };

    return <Editor
        height="80vh"
        defaultLanguage="python"
        theme="vs-dark"
        value={workspace.activeFile.sourceCode}
        onChange={handleEditorChange}
    />;
}