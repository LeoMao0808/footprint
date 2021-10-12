package org.leo;

import com.microsoft.schemas.office.visio.x2012.main.VisioDocumentDocument1;
import com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType;
import org.apache.xmlbeans.XmlException;

import java.io.File;
import java.io.IOException;

/**
 * 模块名称:CRM
 * 模块描述:TODO
 *
 * @author xiaochuang.mao
 * @date 2021/9/13 16:02
 */
public class Test {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\User\\Desktop\\测试测试.vsdx");
        try {
            VisioDocumentDocument1 visioDocumentDocument1 = VisioDocumentDocument1.Factory.parse(file);
            VisioDocumentType visioDocument = visioDocumentDocument1.getVisioDocument();
        } catch (XmlException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
