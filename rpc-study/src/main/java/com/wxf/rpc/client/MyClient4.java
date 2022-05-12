package com.wxf.rpc.client;

import com.dtflys.forest.annotation.Body;
import com.dtflys.forest.annotation.Post;

/**
 * @author weixf
 * @since 2022-01-05
 */
public interface MyClient4 {

    @Post(
            url = "http://10.2.0.38:8001/ui/services/ServiceForBankInterfaceService?wsdl",
            contentType = "text/xml; charset=gbk",
            headers = {"SOAPAction: http://kernel.ablinkbank.sinosoft.com"}
    )
    String service(@Body String param);
}
