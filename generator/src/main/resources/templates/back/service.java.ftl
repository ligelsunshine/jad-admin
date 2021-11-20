<#include "../common/copyright.ftl">

package ${package};

<#if model.treeModel>
import com.jad.common.base.service.TreeService;
<#else>
import com.jad.common.base.service.BaseService;
</#if>
import com.jad.${model.moduleLowerCase}.entity.${model.bigHump};

/**
 * ${model.title}服务接口类
 *
 * @author ${author}
 * @since ${dateTime}
 */
public interface ${model.bigHump}Service extends <#if model.treeModel>TreeService<#else>BaseService</#if><${model.bigHump}> {

}
