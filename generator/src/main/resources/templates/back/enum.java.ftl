<#include "../common/copyright.ftl">

package ${package};

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * ${enumParam.title}
<#list enumParam.enumVal as enumVal>
 * - ${enumVal.title}:${enumVal.name}
</#list>
 *
 * @author ${author}
 * @since ${dateTime}
 */
public enum ${enumParam.bigHump} {
<#list enumParam.enumVal as enumVal>
    ${enumVal.upperCaseUnderline}(${enumVal_index})<#if enumVal_has_next>,<#else>;</#if>
</#list>

    @EnumValue
    @JsonValue
    private final int index;

    ${enumParam.bigHump}(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
