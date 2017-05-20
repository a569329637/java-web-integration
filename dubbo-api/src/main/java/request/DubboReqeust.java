package request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author guishangquan
 * @Description
 * @Package request
 * @date 17/5/20
 **/
@Data
public class DubboReqeust implements Serializable {
    private static final long serialVersionUID = -3907578830349314019L;
    private String id;
    private String name;
}
