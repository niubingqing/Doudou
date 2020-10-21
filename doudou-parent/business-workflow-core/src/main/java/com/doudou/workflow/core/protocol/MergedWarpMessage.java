
package com.doudou.workflow.core.protocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author niubq
 * @date 2020/7/1 15:07
 * @description
 */
public class MergedWarpMessage extends AbstractMessage implements Serializable, MergeMessage {

    /**
     * The Msgs.
     */
    public List<AbstractMessage> msgs = new ArrayList<>();
    /**
     * The Msg ids.
     */
    public List<Integer> msgIds = new ArrayList<>();

    @Override
    public short getTypeCode() {
        return MessageType.TYPE_MERGE_MSG;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SeataMergeMessage ");
        for (AbstractMessage msg : msgs) {
            sb.append(msg.toString()).append("\n");
        }
        return sb.toString();
    }
}
