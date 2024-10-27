import {createContext, useContext} from "react";
import {WorkspaceState} from "./types.ts";

export const WorkspaceContext
    = createContext<WorkspaceState | undefined>(undefined);

export function useWorkspaceContext()  {
    const workspaceState = useContext(WorkspaceContext);

    if (workspaceState == undefined) {
        throw new Error("useWorkspaceContext must be used within WorkspaceContext");
    }

    return workspaceState
}
