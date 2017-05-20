package response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author guishangquan
 * @Description
 * @Package response
 * @date 17/5/20
 **/
@Data
public class DubboResponse implements Serializable {
    private static final long serialVersionUID = 6368664676488440903L;
    private String data;
}
