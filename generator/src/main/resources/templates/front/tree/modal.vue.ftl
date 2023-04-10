<#assign title="${model.title}"/>
<#assign Entity="${model.bigHump}"/>
<#assign entity="${model.smallHump}"/>
<template>
    <BasicModal v-bind="$attrs" @register="registerModal" :title="getTitle" @ok="handleSubmit">
        <BasicForm @register="registerForm"/>
    </BasicModal>
</template>
<script lang="ts">
    import {computed, defineComponent, ref, unref} from 'vue';
    import {BasicModal, useModalInner} from '/@/components/Modal';
    import {BasicForm, useForm} from '/@/components/Form/index';

    import {formSchema, getTreeApi, saveApi, updateApi} from '/@';

    export default defineComponent({
        name: '${Entity}Modal',
        components: {BasicModal, BasicForm},
        emits: ['success', 'register'],
        setup(_, {emit}) {
            const isUpdate = ref(true);

            const [registerForm, {resetFields, setFieldsValue, updateSchema, validate}] = useForm({
                labelWidth: 100,
                schemas: formSchema,
                showActionButtonGroup: false,
            });

            const [registerModal, {setModalProps, closeModal}] = useModalInner(async (data) => {
                await resetFields();
                setModalProps({confirmLoading: false});
                isUpdate.value = !!data?.isUpdate;

                if (unref(isUpdate)) {
                    await setFieldsValue({
                        ...data.record,
                    });
                } else {
                    // 设置上级菜单id
                    data.record = {pid: data.record?.id};
                    await setFieldsValue({
                        ...data.record,
                    });
                }
                const treeData = await getTreeApi();
                await updateSchema({
                    field: 'pid',
                    componentProps: {treeData},
                });
            });

            const getTitle = computed(() => (!unref(isUpdate) ? '新增${title}' : '编辑${title}'));

            async function handleSubmit() {
                try {
                    const values = await validate();
                    setModalProps({confirmLoading: true});
                    // API
                    if (unref(isUpdate)) {
                        await updateApi(values);
                    } else {
                        await saveApi(values);
                    }
                    closeModal();
                    emit('success', {isUpdate: unref(isUpdate), record: values});
                } finally {
                    setModalProps({confirmLoading: false});
                }
            }

            return {registerModal, registerForm, getTitle, handleSubmit};
        },
    });
</script>
