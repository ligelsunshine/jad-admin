<#assign title="${model.title}"/>
<#assign module="${model.module}"/>
<#assign Entity="${model.bigHump}"/>
<#assign entity="${model.smallHump}"/>
<#if model.namespace?trim?length gt 0>
    <#assign requestPath="/${model.namespaceSmallHump}/${model.smallHump}"/>
<#else>
    <#assign requestPath="/${model.smallHump}"/>
</#if>
import { SearchForm } from '/@/components/Table';
import { defHttp } from '/@/utils/http/axios';
import { ${model.bigHump} } from '/@${dataFilePath}';

enum Api {
  Save = '${requestPath}/save',
  Delete = '${requestPath}/delete',
  DeleteArr = '${requestPath}/deleteArr',
  Update = '${requestPath}/update',
  Get = '${requestPath}/get',
  GetPage = '${requestPath}/get/page',
}

/**
 * 添加${model.title}
 */
export const saveApi = (${entity}: ${Entity}) => {
  return defHttp.post({ url: Api.Save, params: ${entity} }, { isTransformResponse: true });
};

/**
 * 删除${model.title}
 */
export const deleteApi = (id: string) => {
  return defHttp.delete({ url: Api.Delete + '/' + id }, { isTransformResponse: true });
};

/**
 * 删除多个${model.title}
 */
export const deleteArrApi = (ids: string[]) => {
  return defHttp.delete(
    { url: Api.DeleteArr, params: { ids: ids } },
    { isTransformResponse: true }
  );
};

/**
 * 修改${model.title}
 */
export const updateApi = (${entity}: ${Entity}) => {
  return defHttp.put({ url: Api.Update, params: ${entity} }, { isTransformResponse: true });
};

/**
 * 获取单条${model.title}
 */
export const getApi = async (id: string) => {
  const response = await defHttp.get({ url: Api.Get + '/' + id });
  return response.data?.data;
};

/**
 * 分页获取${model.title}
 */
export const getPageApi = async (params?: SearchForm) => {
  const response = await defHttp.post({ url: Api.GetPage, params: params });
  return response.data?.data;
};
