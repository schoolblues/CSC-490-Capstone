<#import "/fragments/layout.ftlh" as layout>

<@layout.main extraCss='<link rel="stylesheet" href="/css/error.css">'>

    <div class="error-container">
        <p class="error-code">${status!''}</p>
        <h2 class="error-title">${error!'Something went wrong'}</h2>
        <p class="error-message">
            <#if message?? && message?has_content && message != "No message available">
                ${message}
            <#else>
                The page you were looking for could not be loaded. It may have moved or no longer exists.
            </#if>
        </p>
        <a href="/" class="error-back">Go back home</a>
    </div>

</@layout.main>
