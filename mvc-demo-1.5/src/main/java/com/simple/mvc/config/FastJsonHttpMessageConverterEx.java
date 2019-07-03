package com.simple.mvc.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Configuration
public class FastJsonHttpMessageConverterEx extends FastJsonHttpMessageConverter {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String RET_CODE_KEY = "retCode";
    private static final String RET_MSG_KEY = "retMsg";
    private static final String BODY_KEY = "body";


    public FastJsonHttpMessageConverterEx() {
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setDateFormat(DATE_FORMAT);

        SerializeConfig serializeConfig = SerializeConfig.globalInstance;
        serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
        serializeConfig.put(Long.class, ToStringSerializer.instance);
        serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
        serializeConfig.put(Date.class, new SimpleDateFormatSerializer(DATE_FORMAT));
        fastJsonConfig.setSerializeConfig(serializeConfig);
        setFastJsonConfig(fastJsonConfig);

        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        supportedMediaTypes.add(MediaType.APPLICATION_PDF);
        supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XML);
        supportedMediaTypes.add(MediaType.IMAGE_GIF);
        supportedMediaTypes.add(MediaType.IMAGE_JPEG);
        supportedMediaTypes.add(MediaType.IMAGE_PNG);
        supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        supportedMediaTypes.add(MediaType.TEXT_XML);
        setSupportedMediaTypes(supportedMediaTypes);
    }


    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException {
        if (inputMessage instanceof ClientHttpResponse) {
            type = this.getType(type, contextClass);

            try {
                InputStream in = inputMessage.getBody();
                JSONObject jsonObj = JSON.parseObject(in, getFastJsonConfig().getCharset(), JSONObject.class, getFastJsonConfig().getFeatures());

                if (jsonObj.containsKey(RET_CODE_KEY)) {
                    String retCode = jsonObj.getString(RET_CODE_KEY);

//                    if (type instanceof Class && BeanUtil.isPrimitive((Class<?>) type)) {
//                        if (Date.class.isAssignableFrom((Class<?>) type)) {
//                            return DateUtils.parseDate(jsonObj.getString(BODY_KEY), DATE_FORMAT);
//                        } else if (Long.class.isAssignableFrom((Class<?>) type)) {
//                            return Long.parseLong(jsonObj.getString(BODY_KEY));
//                        }
//                        return jsonObj.get(BODY_KEY);
//                    }
                    return JSON.parseObject(jsonObj.getString(BODY_KEY), type, getFastJsonConfig().getFeatures());
                } else {
                    return jsonObj.toJavaObject(type);
                }
            } catch (JSONException ex) {
                throw new HttpMessageNotReadableException("JSON parse error: " + ex.getMessage(), ex);
            } catch (IOException ex) {
                throw new HttpMessageNotReadableException("I/O error while reading input message", ex);
            }

        }
        return super.read(type, contextClass, inputMessage);
    }

}
