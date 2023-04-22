<#include "../common/copyright.ftl">

package ${package};

<#if model.treeModel>
import com.jad.common.base.service.impl.TreeServiceImpl;
<#else>
import com.jad.common.base.service.impl.BaseServiceImpl;
</#if>
import com.jad.${model.moduleLowerCase}.entity.${model.bigHump};
import com.jad.${model.moduleLowerCase}.mapper.${model.bigHump}Mapper;
import com.jad.${model.moduleLowerCase}.service.${model.bigHump}Service;

import org.springframework.stereotype.Service;

/**
 * ${model.title}服务实现类
 *
 * @author ${author}
 * @since ${dateTime}
 */
@Service
public class ${model.bigHump}ServiceImpl extends <#if model.treeModel>TreeServiceImpl<#else>BaseServiceImpl</#if><${model.bigHump}Mapper, ${model.bigHump}> implements ${model.bigHump}Service {

}
