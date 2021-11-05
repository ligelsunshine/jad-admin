<#assign title="${model.title}"/>
<#assign Entity="${model.bigHump}"/>
<#assign entity="${model.smallHump}"/>
<template>
  <BasicModal v-bind="$attrs" @register="registerModal" title="${title}详情" width="50%">
    <Description class="mt-4" size="middle" :column="3" :data="${entity}" :schema="descSchema" />
  </BasicModal>
</template>
<script lang="ts">
  import {defineComponent, ref} from 'vue';
  import {BasicModal, useModalInner} from '/@/components/Modal';
  import {Description} from '/@/components/Description';

  import {descSchema, getApi} from '/@';

  export default defineComponent({
    name: '${Entity}Modal',
    components: { BasicModal, Description },
    setup() {
      const id = ref('');
      const ${entity} = ref<${Entity}>({ id: '' });
      const [registerModal] = useModalInner(async (data) => {
        id.value = data.record.id;
        ${entity}.value = await getApi(id.value);
      });

      return {
        registerModal,
        ${entity},
        descSchema,
      };
    },
  });
</script>
<style scoped></style>
